package frds.mgnt.service;


import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import frds.mgnt.model.FriendEntity;
import frds.mgnt.model.FriendRepository;
import frds.mgnt.model.FriendState;
import frds.mgnt.model.ProfileEntity;
import frds.mgnt.model.ProfileRepository;

@Service
@Transactional
class DefaultFriendService implements FriendService {

    private final ProfileRepository profileRepository;

    private final FriendRepository friendRepository;

    public DefaultFriendService(ProfileRepository profileRepository, FriendRepository friendRepository) {
        this.profileRepository = profileRepository;
        this.friendRepository = friendRepository;
    }

    public void addFriends(List<String> emailAddresses) throws FriendRuntimeException {
        List<String> friendsToAdd = new ArrayList<>(emailAddresses);
        emailAddresses.stream()
                .map(this::getOrCreateProfile)
                .forEach(profile -> friendsToAdd.stream()
                        .filter(friendToAdd -> canAddAsFriend(profile, friendToAdd))
                        .forEach(friendToAdd -> {
                            ProfileEntity friendProfile = getOrCreateProfile(friendToAdd);
                            createFriendship(profile, friendProfile);
                        })
                );
    }

    private ProfileEntity getOrCreateProfile(String emailAddress) {
        Optional<ProfileEntity> existingProfile = profileRepository.findByEmailAddress(emailAddress);
        return existingProfile.orElseGet(() -> profileRepository.save(new ProfileEntity(null,emailAddress, emailAddress.split("@")[0])));
    }

    private boolean canAddAsFriend(ProfileEntity ownerProfile, String friendToAdd) {
        if (friendToAdd.equalsIgnoreCase(ownerProfile.getEmailAddress())) {
            return false;
        }
        ProfileEntity friendProfile = getOrCreateProfile(friendToAdd);
        Optional<FriendEntity> optional = friendRepository.findByOwnerAndFriend(ownerProfile, friendProfile);
        if (!optional.isPresent()) {
            return true;
        }
        FriendEntity friendship = optional.get();
        return FriendState.BLOCKED != friendship.getState() && FriendState.FRIEND != friendship.getState();
    }

    private void createFriendship(ProfileEntity ownerProfile, ProfileEntity friendProfile) {
        Optional<FriendEntity> optional = friendRepository.findByOwnerAndFriend(ownerProfile, friendProfile);
        if (optional.isPresent()) {
            FriendEntity friendship = optional.get();
            friendship.setFlag(true);
            friendship.setState(FriendState.FRIEND);
        }
        friendRepository.save(new FriendEntity(ownerProfile, friendProfile, true, FriendState.FRIEND));
    }

    @Transactional(readOnly = true)
    public List<String> getFriendsList(String emailAddress) throws FriendRuntimeException {
        Optional<ProfileEntity> optional = profileRepository.findByEmailAddress(emailAddress);
        return optional.map(profileEntity -> friendRepository.findByOwnerAndFlagAndState(profileEntity, true, FriendState.FRIEND).stream()
                .map(FriendEntity::getFriend)
                .map(ProfileEntity::getEmailAddress)
                .collect(toList())).orElse(emptyList());
    }

    @Transactional(readOnly = true)
    public List<String> getCommonFriends(List<String> emailAddresses) throws FriendRuntimeException {
        Map<String, List<String>> friendMap = new HashMap<>();
        emailAddresses.forEach(emailAdd -> {
            Optional<ProfileEntity> optional = profileRepository.findByEmailAddress(emailAdd);
            List<String> friends = optional.map(owner -> friendRepository.findByOwnerAndFlagAndState(owner, true, FriendState.FRIEND)
                    .stream().map(FriendEntity::getFriend).map(ProfileEntity::getEmailAddress).collect(toList()))
                    .orElse(emptyList());
            friendMap.put(emailAdd, friends);
        });
        Iterator<List<String>> iterator = friendMap.values().iterator();
        Set<String> commonEmail = new LinkedHashSet<>(iterator.next());
        while (iterator.hasNext()) {
            commonEmail.retainAll(iterator.next());
        }

        return new ArrayList<>(commonEmail);
    }

    public void subscribe(String requestorEmail, String targetEmail) throws FriendRuntimeException {
        ProfileEntity ownerProfile = getOrCreateProfile(requestorEmail);
        ProfileEntity targetProfile = getOrCreateProfile(targetEmail);
        Optional<FriendEntity> optional = friendRepository.findByOwnerAndFriend(ownerProfile, targetProfile);
        if (!optional.isPresent()) {
            friendRepository.save(new FriendEntity(ownerProfile, targetProfile, false, FriendState.SUBSCRIBED));
        }
    }

    public void block(String requestorEmail, String targetEmail) throws FriendRuntimeException {
        ProfileEntity ownerProfile = getOrCreateProfile(requestorEmail);
        ProfileEntity targetProfile = getOrCreateProfile(targetEmail);
        Optional<FriendEntity> optional = friendRepository.findByOwnerAndFriend(ownerProfile, targetProfile);
        if (optional.isPresent()) {
            FriendEntity subscription = optional.get();
            subscription.setFlag(false);
            subscription.setState(FriendState.BLOCKED);
        }
        else {
            friendRepository.save(new FriendEntity(ownerProfile, targetProfile, false, FriendState.BLOCKED));
        }
    }

    public List<String> notify(String sender, String text) throws FriendRuntimeException {
        List<String> notifiable = new ArrayList<>();
        ProfileEntity senderProfile = getOrCreateProfile(sender);
        friendRepository.findByOwnerAndStateNot(senderProfile, FriendState.BLOCKED).stream()
                .map(FriendEntity::getFriend)
                .map(ProfileEntity::getEmailAddress)
                .forEach(notifiable::add);
        List<String> mentions = parseText(text);
        mentions.stream()
                .map(profileRepository::findByEmailAddress)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(profile -> friendRepository.findByOwnerAndFriend(senderProfile, profile))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(friend -> friend.getState() != FriendState.BLOCKED)
                .map(FriendEntity::getFriend)
                .map(ProfileEntity::getEmailAddress)
                .forEach(notifiable::add);
        return notifiable;
    }

    private List<String> parseText(String text) {
        Pattern emailPattern = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
        List<String> emails = new ArrayList<>();
        Matcher matcher = emailPattern.matcher(text);
        matcher.results().map(MatchResult::group).forEach(emails::add);
        return emails;
    }
}

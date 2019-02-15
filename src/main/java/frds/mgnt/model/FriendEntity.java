package frds.mgnt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "friends", uniqueConstraints = { @UniqueConstraint(name = "friendship_uk", columnNames = {
		FriendEntity.COL_OWNER_ID, FriendEntity.COL_FRIEND_ID }) })
public class FriendEntity extends BaseEntity implements Serializable {

	public static final String COL_OWNER_ID = "owner_id";

	public static final String COL_FRIEND_ID = "friend_id";

	private static final long serialVersionUID = -4264420370712528192L;

	@ManyToOne
	@JoinColumn(name = COL_OWNER_ID, nullable = false)
	private ProfileEntity owner;

	@ManyToOne
	@JoinColumn(name = COL_FRIEND_ID, nullable = false)
	private ProfileEntity friend;

	@Column(nullable = false)
	private boolean flag;

	@Enumerated
	@Column(nullable = false)
	private FriendState state;

	public FriendEntity(Integer id, ProfileEntity owner, ProfileEntity friend, boolean flag, FriendState state) {
		super(id);
		this.owner = owner;
		this.friend = friend;
		this.flag = flag;
		this.state = state;
	}
}

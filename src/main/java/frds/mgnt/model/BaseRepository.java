package frds.mgnt.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Integer> {}

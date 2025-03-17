package lamda511.webtestingtool.repositories;

import lamda511.webtestingtool.entities.TestEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TestsRepository extends CrudRepository<TestEntity, String> {

    @Query("select TestEntity from TestEntity t where t.id = :id")
    TestEntity findByTestId(@Param("id") String id);
}

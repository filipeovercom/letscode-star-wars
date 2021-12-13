package br.com.letscode.starwarsnetwork.domain.repository;

import br.com.letscode.starwarsnetwork.domain.model.dto.ResourcesAverageResponse;
import br.com.letscode.starwarsnetwork.domain.model.dto.ResourcesSumResponse;
import br.com.letscode.starwarsnetwork.domain.model.entity.Soldier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SoldierRepository extends CrudRepository<Soldier, Long> {

    Long countByTraitorTrue();

    @Query(
            "select "
                    + "new br.com.letscode.starwarsnetwork.domain.model.dto.ResourcesAverageResponse("
                    + "avg(i.water), "
                    + "avg(i.food), "
                    + "avg(i.weapon), "
                    + "avg(i.ammo)) "
                    + "from Soldier s "
                    + "inner join s.inventory i "
                    + "where s.traitor = FALSE")
    ResourcesAverageResponse findAverageByResources();

    @Query(
            "select new br.com.letscode.starwarsnetwork.domain.model.dto.ResourcesSumResponse("
                    + "sum(i.water), "
                    + "sum(i.food), "
                    + "sum(i.weapon), "
                    + "sum(i.ammo)) from Soldier s inner join s.inventory i where s.traitor = true")
    ResourcesSumResponse findSumResourcesByTraitors();
}

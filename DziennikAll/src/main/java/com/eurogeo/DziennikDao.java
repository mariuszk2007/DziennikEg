package com.eurogeo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface DziennikDao extends CrudRepository<DziennikAll, Long> {
DziennikAll findByDay(LocalDate day);
DziennikAll findByDayAndUser(LocalDate day, String userName);
DziennikAll findByUser(String userName);
List<DziennikAll> findByRobota(String robota);

List<DziennikAll> findByMonth(int month);
}

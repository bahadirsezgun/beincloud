package com.beinplanner.test.schedule;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import tr.com.beinplanner.schedule.dao.SchedulePlan;
import tr.com.beinplanner.schedule.repository.SchedulePlanRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SchedulePlanTest {

	
	
	
	@Autowired
	SchedulePlanRepository schedulePlanRepository;
	
	@Test
    public void testSerialize() throws Exception {
        SchedulePlan schedulePlan = schedulePlanRepository.findOne(Long.parseLong("1"));
        // Assert against a `.json` file in the same package as the test
       assertNotNull(schedulePlan);
    } 
	
	
}

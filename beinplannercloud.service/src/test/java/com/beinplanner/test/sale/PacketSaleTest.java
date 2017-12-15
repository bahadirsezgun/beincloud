package com.beinplanner.test.sale;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import tr.com.beinplanner.packetsale.dao.PacketSalePersonal;
import tr.com.beinplanner.packetsale.service.PacketSaleService;

@EnableAutoConfiguration
@ComponentScan(basePackages={"com.beinplanner","tr.com.beinplanner"})
@EntityScan(basePackages={"tr.com.beinplanner"})
@EnableJpaRepositories("tr.com.beinplanner")
@RunWith(SpringRunner.class)
@SpringBootTest 
public class PacketSaleTest {

	@Configuration
    static class ContextConfiguration {
      /*
		@Autowired
		PacketSaleClassRepository packetSaleClassRepository;

		@Autowired
		PacketSalePersonalRepository packetSalePersonalRepository;
		
		@Autowired
		PacketSaleMembershipRepository packetSaleMembershipRepository;
		*/
		// this bean will be injected into the OrderServiceTest class
        @Bean
        public PacketSaleService packetSaleService() {
        	PacketSaleService packetSaleService = new PacketSaleService();
            // set properties, etc.
            return packetSaleService;
        }
   }
	
	
	@Autowired
	PacketSaleService packetSaleService;
	
	
	
	@Test
	public void findPacketSalePersonal() {
		PacketSalePersonal packetSalePersonal= packetSaleService.findPacketSalePersonalById(37L);
		assertTrue(packetSalePersonal!=null);
	}
}

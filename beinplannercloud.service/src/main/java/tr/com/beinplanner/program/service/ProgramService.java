package tr.com.beinplanner.program.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import tr.com.beinplanner.program.dao.ProgramMembership;
import tr.com.beinplanner.program.dao.ProgramPersonal;
import tr.com.beinplanner.program.repository.ProgramClassRepository;
import tr.com.beinplanner.program.repository.ProgramMembershipDetailRepository;
import tr.com.beinplanner.program.repository.ProgramMembershipRepository;
import tr.com.beinplanner.program.repository.ProgramPersonalRepository;
import tr.com.beinplanner.result.HmiResultObj;

@Service
@Qualifier("programService")
public class ProgramService {

	@Autowired
	ProgramClassRepository programClassRepository;
	
	@Autowired
	ProgramPersonalRepository programPersonalRepository;

	@Autowired
	ProgramMembershipRepository programMembershipRepository;

	@Autowired
	ProgramMembershipDetailRepository programMembershipDetailRepository;
	
	
	public ProgramPersonal findProgramPersonalById(long progId) {
		return programPersonalRepository.findOne(progId);
	}
	
	public ProgramMembership findProgramMembershipById(long progId) {
		return programMembershipRepository.findOne(progId);
	}
	
	public ProgramMembership createProgramMembershipById(ProgramMembership programMembership) {
		return programMembershipRepository.save(programMembership);
	}
}

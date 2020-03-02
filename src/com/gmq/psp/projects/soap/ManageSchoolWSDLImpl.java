package com.gmq.psp.projects.soap;

import java.util.ArrayList;

public class ManageSchoolWSDLImpl implements ManageSchoolWSDL {

	ArrayList<SubjectRequest> SubjectList = new ArrayList<SubjectRequest>();
	ArrayList<StudentRequest> StudentList = new ArrayList<StudentRequest>();
	ArrayList<AssessmentRequest> AssessmentList = new ArrayList<AssessmentRequest>();
	
	@Override
	public SubjectResponse registerSubject(SubjectRequest sentSubject) {
		SubjectResponse confirmer = new SubjectResponse();
		if(validSubject(sentSubject)!=null) {
			confirmer.setConfirmation(validSubject(sentSubject) + " already in use.");
		}else {
			SubjectList.add(sentSubject);
			confirmer.setConfirmation(sentSubject.getSubjectName() + " has been added to the subject list.");
		}
		return confirmer;
	}

	@Override
	public StudentResponse registerStudent(StudentRequest sentStudent) {
		StudentResponse confirmer = new StudentResponse();
		String newStudentID = sentStudent.getStudentID();
		if(validId(newStudentID)) {
			if(studentIdExists(newStudentID)) {
				confirmer.setConfirmation("ID already in use.");
			}else {
				StudentList.add(sentStudent);
				confirmer.setConfirmation(sentStudent+" with identification number: " + newStudentID +" has been added to the student list.");
			}
			
		}else {
			confirmer.setConfirmation("ID is not of valid format");
		}
		
		return confirmer;
	}

	@Override
	public AssessmentResponse startAssessment(AssessmentRequest sentAssessment) {
		AssessmentResponse confirmer = new AssessmentResponse();
		String[] invalidData = new String[5];
		int invalid = 0;
		
		if(sentAssessment.getFinalGrade()<0||sentAssessment.getFinalGrade()>10) {
			invalidData[invalid] = "grade out of bounds";
		}
		if(validId(sentAssessment.getAssessedStudent())) {
			if(!(studentIdExists(sentAssessment.getAssessedStudent()))) {
				invalidData[invalid] = "student does not exist";
			}
		}else {
			invalidData[invalid] = "invalid ID format";
		}
		if(!(subjectIdExists(sentAssessment.getAssessedSubject()))) {
			invalidData[invalid] = "subject does not exist";
		}
		if(invalid!=0) {
			String invalidMessage = "The following problems were found with your request: ";
			for(int i = 0;i<invalid;i++) {
				if(i!=0) {
					invalidMessage+=", ";
				}
				invalidMessage+= invalidData[i];
			}
			invalidMessage+=".";
			confirmer.setConfirmation(invalidMessage);
		}else {
			AssessmentList.add(sentAssessment);
			confirmer.setConfirmation("Assessment of the student with ID "+sentAssessment.getAssessedStudent()+" in "+sentAssessment.getAssessedSubject()+" with a grade of "+sentAssessment.getFinalGrade()+" has been saved.");
		}		
		return confirmer;
	}
	
	//Validity methods
	
	public boolean studentIdExists(String id) {
		for(StudentRequest current : StudentList) {
			if(id.equals(current.getStudentID())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean validId(String id) {
		if(id.length()!=9) {
			return false;
		}		
		if(!(id.matches("\\d{8][A-Z]$"))) {	
			return false;
		}		
		return true;
		
		
			
	}
	
	public boolean subjectIdExists(String name) {
		for(SubjectRequest current : SubjectList) {
			if(current.getSubjectName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public String validSubject(SubjectRequest sentSubject) {
		String validity="";
		int invalidData = 0;
		
		for(SubjectRequest current : SubjectList) {
			if(current.getSubjectID() == sentSubject.getSubjectID()) {
				validity += "subject ID is";
				invalidData++;
			}
			if(current.getSubjectName().equals(sentSubject.getSubjectName())) {
				validity += "subject name is";
				invalidData++;
			}
		}
		switch(invalidData) {
		case 0:
			return null;
		case 1:
			return validity;
		case 2:
			return "Both are";
		
		default:
			return "Something went wrong";
		}			
	}

}

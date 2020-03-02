package com.gmq.psp.projects.soap;

import java.util.ArrayList;

public class ManageSchoolWSDLImpl implements ManageSchoolWSDL {

	//All three request types will be stored within the corresponding arraylist created below
	
	ArrayList<SubjectRequest> SubjectList = new ArrayList<SubjectRequest>();
	ArrayList<StudentRequest> StudentList = new ArrayList<StudentRequest>();
	ArrayList<AssessmentRequest> AssessmentList = new ArrayList<AssessmentRequest>();
	
	static ConfirmationResponse confirmer = new ConfirmationResponse();
	//Confirmation response is used the treatment of all requests so we can just use a generic static one created here
	
	@Override
	public ConfirmationResponse registerSubject(SubjectRequest sentSubject) {
		if(validSubject(sentSubject)!=null) {//This checks if the subject name or ID already exists
			confirmer.setConfirmation(validSubject(sentSubject) + " already in use.");
		}else {
			SubjectList.add(sentSubject);//If neither are in use it is added to the list and a corresponding success message is sent back
			confirmer.setConfirmation(sentSubject.getSubjectName() + " has been added to the subject list.");
		}
		return confirmer;
	}

	@Override
	public ConfirmationResponse registerStudent(StudentRequest sentStudent) {
		String newStudentID = sentStudent.getStudentID();//The new student ID
		if(validId(newStudentID)) {//We check if the ID is valid (i.e 8 digits and 1 letter)
			if(studentIdExists(newStudentID)) {//Once we know it is valid we check if it already exists
				confirmer.setConfirmation("ID already in use.");//If it does exist we set a corresponding confirmation message
			}else {
				StudentList.add(sentStudent);//If not we add it to the corresponding list and send a success message back
				confirmer.setConfirmation(sentStudent.getStudentName() +" with identification number: " + newStudentID +" has been added to the student list.");
			}
			
		}else {
			confirmer.setConfirmation("ID is not of valid format");//If the ID is not of valid format we set this as our error message
		}
		
		return confirmer;
	}

	@Override
	public ConfirmationResponse startAssessment(AssessmentRequest sentAssessment) {
		String[] invalidData = new String[4];//Here we store all possible failed data entry values
		int invalid = 0;//Our failed data counter
		
		if(sentAssessment.getFinalGrade()<0||sentAssessment.getFinalGrade()>10) {
			invalidData[invalid] = "grade out of bounds";//Grades are only allowed between 0 and 10, anything else is invalid
			invalid++;
		}
		if(validId(sentAssessment.getAssessedStudent())) {
			if(!(studentIdExists(sentAssessment.getAssessedStudent()))) {//The student must exist to be evaluated, if not the assessment is invalid
				invalidData[invalid] = "student does not exist";
				invalid++;
			}
		}else {
			invalidData[invalid] = "invalid ID format";//In case the ID is in the wrong format we know it is invalid so no need to check our list
			invalid++;
		}
		if(!(subjectNameExists(sentAssessment.getAssessedSubject()))) {//The subject must exist for us to evaluate a student in it
			invalidData[invalid] = "subject does not exist";
			invalid++;
		}
		if(invalid!=0) {
			String invalidMessage = "The following problems were found with your request: ";//Here we tally all the failed data entries into a string and add it to the return message
			for(int i = 0;i<invalid;i++) {
				if(i!=0) {
					invalidMessage+=", ";
				}
				invalidMessage+= invalidData[i];
			}
			invalidMessage+=".";
			confirmer.setConfirmation(invalidMessage);
		}else {
			AssessmentList.add(sentAssessment);//if all went well the final assessment is added to the list and a full success message of what was saved is sent back
			confirmer.setConfirmation("Assessment of the student with ID "+sentAssessment.getAssessedStudent()+" in "+sentAssessment.getAssessedSubject()+" with a grade of "+sentAssessment.getFinalGrade()+" has been saved.");
		}		
		return confirmer;
	}
	
	//Validity methods
	
	//This method goes through our list comparing the given ID with the Ids in our student list
	public boolean studentIdExists(String id) {
		for(StudentRequest current : StudentList) {
			if(id.equals(current.getStudentID())) {
				return true;
			}
		}
		return false;
	}
	//This method is used to validate the length and format of the ID (8 numbers + one capital Letter)
	public boolean validId(String id) {
		if(id.length()!=9) {
			return false;
		}		
		if(!(id.matches("\\d{8}[A-Z]"))) {	
			return false;
		}		
		return true;
		
		
			
	}
	//This method checks if the subject name exists in our list
	public boolean subjectNameExists(String name) {
		for(SubjectRequest current : SubjectList) {
			if(current.getSubjectName().toLowerCase().equals(name.toLowerCase())) {//lower case subjects since we dont want repeats like Maths and maths or MATHS
				return true;
			}
		}
		return false;
	}
	
	public String validSubject(SubjectRequest sentSubject) {//Checks validity of subject when adding to list
		String validity="";
		int invalidData = 0;
		
		if(subjectNameExists(sentSubject.getSubjectName())) {//Here we use above method to check if its a unique name
			validity+= "subject name is";
			invalidData++;
		}
		for(SubjectRequest current : SubjectList) {//we also check if the id is not in use
			if(current.getSubjectID() == sentSubject.getSubjectID()) {
				validity += "subject ID is";
				invalidData++;
			}
		}
		switch(invalidData) {
		case 0:
			return null;//null is the ideal return value since we are returning what is invalid, if its null nothing is invalid
		case 1:
			return validity;//validity stores what went wrong in order to show it when we send it back
		case 2:
			return "Both are";//If both are wrong this message is more appropriate
		
		default:
			return "Something went wrong";//This should never happen
		}			
	}

}

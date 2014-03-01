/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ActiveRecord;

/**
 *
 * @author Vidak
 */
public class ExpertiseExperienceContainer{
	public Expertise expertise;
	public ApplicantExperience experience;
	
	public ExpertiseExperienceContainer(Expertise expertise, ApplicantExperience applicantExperience){
	    this.expertise = expertise;
	    this.experience = applicantExperience;
	}
	
	public Expertise getExpertise(){
	    return expertise;
	}
	public ApplicantExperience getApplicantExperience(){
	    return experience;
	}
    }
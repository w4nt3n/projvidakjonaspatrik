/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package ActiveRecord;

import java.util.ArrayList;

/**
 *
 * @author Vidak
 */
 
public interface ExpertiseDAO 
{
    public Expertise getExpertiseWithId(int ID);
    public int getIdWithExpertise(String expertise);
    public ArrayList<Expertise> getAllExpertises();
}
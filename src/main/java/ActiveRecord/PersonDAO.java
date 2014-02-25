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
 
public interface PersonDAO 
{
	public void insert(Person person);
	public Person findByPersonId(int custId);
}
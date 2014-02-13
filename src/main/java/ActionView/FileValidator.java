/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ActionView;

/**
 *
 * @author Jonas
 */
import org.springframework.validation.Errors;  
import org.springframework.validation.Validator;  
import ActiveRecord.FileUploadBean;  
  
public class FileValidator implements Validator {  
  
 @Override  
 public boolean supports(Class<?> arg0) {  
  // TODO Auto-generated method stub  
  return false;  
 }  
  
 @Override  
 public void validate(Object uploadedFile, Errors errors) {  
  
  FileUploadBean file = (FileUploadBean) uploadedFile;  
  
  if (file.getFile().getSize() == 0) {  
   errors.rejectValue("file", "uploadForm.salectFile",  
     "Please select a file!");  
  }  
  
 }  
  
}  
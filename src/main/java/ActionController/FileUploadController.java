/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ActionController;

/**
 *
 * @author Jonas
 */
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Controller;  
import org.springframework.validation.BindingResult;  
import org.springframework.web.bind.annotation.ModelAttribute;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.multipart.MultipartFile;  
import org.springframework.web.servlet.ModelAndView;  
  
import ActiveRecord.FileUploadBean;  
import ActionView.FileValidator;  
  
@Controller  
public class FileUploadController {  
  
 @Autowired  
 FileValidator fileValidator;  
  
 @RequestMapping("/fileUploadForm")  
 public ModelAndView getUploadForm(  
   @ModelAttribute("uploadedFile") FileUploadBean uploadedFile,  
   BindingResult result) {  
  return new ModelAndView("uploadForm");  
 }  
  
 @RequestMapping("/fileUpload")  
 public ModelAndView fileUploaded(  
   @ModelAttribute("uploadedFile") FileUploadBean uploadedFile,  
   BindingResult result) {  
  InputStream inputStream = null;  
  OutputStream outputStream = null;  
  
  MultipartFile file = uploadedFile.getFile();  
  fileValidator.validate(uploadedFile, result);  
  
  String fileName = file.getOriginalFilename();  
  
  if (result.hasErrors()) {  
   return new ModelAndView("uploadForm");  
  }  
  
  try {  
   inputStream = file.getInputStream();  
  
   File newFile = new File("C:/temp/" + fileName);  
   if (!newFile.exists()) {  
    newFile.createNewFile();  
   }  
   outputStream = new FileOutputStream(newFile);  
   int read = 0;  
   byte[] bytes = new byte[1024];  
  
   while ((read = inputStream.read(bytes)) != -1) {  
    outputStream.write(bytes, 0, read);  
   }  
  } catch (IOException e) {  
   // TODO Auto-generated catch block  
   e.printStackTrace();  
  }  
  
  return new ModelAndView("showFile", "message", fileName);  
 }  
  
}  
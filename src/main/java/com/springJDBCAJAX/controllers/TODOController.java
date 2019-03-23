package com.springJDBCAJAX.controllers;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.springJDBCAJAX.Model.TODO;
import com.springJDBCAJAX.data.TODODao;
import com.springJDBCAJAX.service.EmailService;

@RestController
@Transactional(rollbackFor = Exception.class)
public class TODOController {
	
	@Autowired  
    TODODao dao;  
      
	@Autowired
	EmailService ec;
	
   public TODOController( TODODao dao)
   		{
	   this.dao=dao;
	   }

   @RequestMapping("/todoform")  
   public ModelAndView showform(){  
       return new ModelAndView("todoform","command",new TODO());  
   }  
   
   //Amount of todo data on JSP page
	@RequestMapping(value="/todoview/{pageID}")  
    public ModelAndView edit(@PathVariable("pageID") int pageId){
        //int total=5;
		int total = 50;
        if(pageId==1){}  
        else{  
        	pageId=(pageId-1)*total+1;  
        }  
        List<TODO> list=dao.getEmployeesByPage(pageId,total);  
          
        return new ModelAndView("todoview","list",list);
       }  
	
	//To add new todo
	@RequestMapping(value="/save",method = RequestMethod.POST)  
    public ModelAndView save(@ModelAttribute("todo") TODO todo) throws AddressException, MessagingException, IOException{  
        dao.save(todo);  
        ec.sendSaveMail();
        return new ModelAndView("redirect:/todoview/1");//will redirect to todoview request mapping  
    }  
  
	//To update todo
	@RequestMapping(value="/edittodo/{id}")  
	
    public ModelAndView edit1(@PathVariable int id){  
        TODO todo=dao.getEmpById(id);  
        return new ModelAndView("todoeditform","command",todo);  
    } 
	
	//To delete todo
    @RequestMapping(value="/deletetodo/{id}",method = RequestMethod.GET)  
    public ModelAndView delete(@PathVariable int id)throws AddressException, MessagingException, IOException{  
        dao.delete(id);  
        ec.sendDeleteMail();
        return new ModelAndView("redirect:/todoview/1");  
    }  
    
    //To redirect after adding todo
    @RequestMapping(value="/editsave",method = RequestMethod.POST)  
    public ModelAndView editsave(@ModelAttribute("todo") TODO todo)throws AddressException, MessagingException, IOException{  
        dao.update(todo);  
        ec.sendUpdateMail();
        return new ModelAndView("redirect:/todoview/1");  
    }
    
}

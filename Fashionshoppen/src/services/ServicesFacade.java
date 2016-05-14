/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.ResultSet;


/**
 *
 * @author jonaspedersen
 */
public final class ServicesFacade {
    
    private static ServicesFacade instance = null;
    public ServerRequest sr;
    public GetUserCallback userCallback;
    
    public ServicesFacade(){
        sr = new ServerRequest();
    }
    
        public static ServicesFacade getInstance(){
        
        if(instance == null){
            instance = new ServicesFacade();
        }
        
        return instance;
    }
        
    public void browseProductName(String name){
        sr.browseProductName(name);
    }
    
    public void browseCategory(String category, String name){
        sr.browseCategory(category, name);
    }
    
    public ResultSet loginUser(String email, String password){
    return sr.loginUser(email, password);
    
    }
    
    public void registerUser(String firstName, String lastName, String email, String password){
     sr.registerUser(firstName, lastName, email, password);
    }
        
    public ResultSet loginCustomer(int user_id){
     return sr.checkLoginType();
    }
}

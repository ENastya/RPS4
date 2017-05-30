/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import client.Clientmeth;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Настя
 */

@Named(value = "commandbean")
@RequestScoped
public class commandbean {

    @EJB
    private Clientmeth cm;
    
    public String money(int id, int sum){
        if (cm.platezh(id, sum)){
    return "успешно выполнено";}
        else return "недостаточно средств";
    }
    
    public String moneyOtkat(int id, int sum){
        if (cm.platezhOtkat(id, sum)){
    return "откат успешно выполнен";}
        else return "недостаточно средств";
    }
    
    public String moneyBankEx(int id, int sum){
        if (cm.platezhOtkat(id, sum)){
    return "успешно";}
        else return "недостаточно средств";
    }
    
    public String moneyOtkatNT(int id, int sum){
        if (cm.platezhOtkatNT(id, sum)){
    return "откат успешно выполнен";}
        else return "недостаточно средств";
    }
    
    public String moneyOtkatRN(int id, int sum){
        if (cm.platezhOtkatNT(id, sum)){
    return "откат успешно выполнен";}
        else return "недостаточно средств";
    }
    
    public commandbean() {
    }
    
}

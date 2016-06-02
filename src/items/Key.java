/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package items;

import core.Item;

/**
 *
 * @author pedro
 */
public class Key extends Item{
    
    public Key(String name, String description){
        super();
        setUsageKey(Item.TAKE,1);
        setName(name);
        if(!name.equals("key)")) addSynonym(name);
        addSynonym("key");
        setDescription(description);
    }
    
    public Key(){
        super();
        setUsageKey(Item.TAKE,1);
        setName("Key");
        addSynonym("key");
        setDescription("A small, yellow key");
    }
    
}

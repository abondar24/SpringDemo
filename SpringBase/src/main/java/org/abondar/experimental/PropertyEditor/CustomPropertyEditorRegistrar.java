package org.abondar.experimental.PropertyEditor;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by abondar on 06.07.16.
 */
public class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {
    @Override
    public void registerCustomEditors(PropertyEditorRegistry propertyEditorRegistry) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM//yyyy");
        propertyEditorRegistry.registerCustomEditor(DateFormat.class,new CustomDateEditor(dateFormat,true));
        propertyEditorRegistry.registerCustomEditor(String.class,new StringTrimmerEditor(true));
    }
}

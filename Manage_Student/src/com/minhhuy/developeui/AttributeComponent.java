package com.minhhuy.developeui;

import java.awt.*;

import javax.swing.*;

public class AttributeComponent {
	public final static String LABEL = "class javax.swing.JLabel";
	public final static String TEXT_FIELD = "class javax.swing.JTextField";
	public final static String COMBO_BOX = "class javax.swing.JComboBox";
	public final static String TABLE = "class javax.swing.JTable";
	public final static String LIST = "class javax.swing.JList";
	
	public static void set(Component comp, Font font, String typeComp) {
		if (comp instanceof JPanel) {
			Component[] container = ((JPanel)comp).getComponents();
			for (Component contain : container) {
				set(contain, font, typeComp);
			}
		}else {
			if (comp.getClass().toString().equals(typeComp)) {	
				comp.setFont(font);
			}
				
		}	
	}	

	public static void set(Component comp, Color color, String typeComp) {
		if (comp instanceof JPanel) {
			Component[] container = ((JPanel)comp).getComponents();
			for (Component contain : container) {
				set(contain, color, typeComp);
			}
		}else {
			if (comp.getClass().toString().equals(typeComp)) {
				comp.setForeground(color);
			}
		}	
	}	
	public static void set(Component comp, Dimension dimension, String typeComp) {
		if (comp instanceof JPanel) {
			Component[] container = ((JPanel)comp).getComponents();
			for (Component contain : container) {
				set(contain, dimension, typeComp);
			}
		}else {
			if (comp.getClass().toString().equals(typeComp)) 
				comp.setPreferredSize(dimension);
		}
	}
	public static void set(Component comp, Font font, Color color, String typeComp) {
		if (comp instanceof JPanel) {
			Component[] container = ((JPanel)comp).getComponents();
			for (Component contain : container) {
				set(contain, font, color, typeComp);
			}
		}else {
			if (comp.getClass().toString().equals(typeComp)) {
				comp.setFont(font);
				comp.setForeground(color);
			}
		}	
	}	

	public static void set(Component comp, Font font, Color color, Dimension dimension, String typeComp) {
		if (comp instanceof JPanel) {
			Component[] container = ((JPanel)comp).getComponents();
			for (Component contain : container) {
				set(contain, font, color, typeComp);
			}
		}else {
			if (comp.getClass().toString().equals(typeComp)) {
				comp.setFont(font);
				comp.setForeground(color);
				comp.setPreferredSize(dimension);
			}
		}	
	}
}

package userUI.mycomponents;

import javax.swing.JPopupMenu;

public class Popupconstant extends JPopupMenu{
	Boolean isHideAllowed;
	public Popupconstant(){
		super();
		isHideAllowed = false;
	}
    @Override
    public void setVisible(boolean visibility){
        if(isHideAllowed && !visibility)
            super.setVisible(false);
        else if(!isHideAllowed && visibility)
            super.setVisible(true);
    }

    public void closePopup(){
        this.isHideAllowed = true;
        this.setVisible(false);
    }
}

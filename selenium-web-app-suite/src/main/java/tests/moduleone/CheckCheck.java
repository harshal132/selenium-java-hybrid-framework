package tests.moduleone;

import common.constants.FilePath;
import utils.DataLoader;

public class CheckCheck {
    public static void main(String[] args){
        new CheckCheck().getClassName();
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public void getClassName(){
        System.out.println(this.getClass().getSimpleName());
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
    }
}

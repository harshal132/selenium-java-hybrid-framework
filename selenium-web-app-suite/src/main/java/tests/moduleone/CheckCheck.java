package tests.moduleone;

import common.constants.FilePath;
import utils.DataLoader;

public class CheckCheck {
    public static void main(String[] args){
        System.out.println(new DataLoader().getAppData(FilePath.REAL_APP_DATA_FILE_PATH,"implicitTimeOut"));
    }
}

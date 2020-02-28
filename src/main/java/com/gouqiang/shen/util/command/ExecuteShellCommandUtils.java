package com.gouqiang.shen.util.command;

import com.gouqiang.shen.constant.ReturnConstantsEnum;
import com.gouqiang.shen.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Slf4j
public class ExecuteShellCommandUtils {

    /**
     * 执行Shell命令
     * @param shellPath
     */
     public static void exec(String shellPath){
         try{
             Runtime rt = Runtime.getRuntime();
             Process proc = rt.exec(shellPath,null,null);
             InputStream stderr =  proc.getInputStream();
             InputStreamReader isr = new InputStreamReader(stderr,"GBK");
             BufferedReader br = new BufferedReader(isr);
             String line="";
             while ((line = br.readLine()) != null) {
                 log.info(line);
             }
             log.info("脚本执行完毕");
         }catch (Exception e){
             log.error(ReturnConstantsEnum.EXECUTE_COMMAND_SHELL_FAIL.getDesc(), e);
             throw new BusinessException(ReturnConstantsEnum.EXECUTE_COMMAND_SHELL_FAIL, e);
         }
     }

//    public static void main(String[] args) {
//        exec("F:\\GitHub\\V2RayUI\\src\\main\\resources\\shell\\test.bat");
//    }
}

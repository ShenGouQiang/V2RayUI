package com.gouqiang.shen.util.loc;

import com.gouqiang.shen.constant.Constants;
import com.gouqiang.shen.constant.ReturnConstantsEnum;
import com.gouqiang.shen.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;

/**
 * loc文件工具类
 *
 * @author shengouqiang
 */
@Slf4j
public class LocUtils {

    /**
     * 读取loc文件
     *
     * @param filePath
     * @return
     */
    public static List<String> readLocFile(String filePath) {
        List<String> resultList = new ArrayList<>();
        BufferedReader reader = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new BusinessException(ReturnConstantsEnum.TEMPLATE_FEIL_NOT_EXIT);
            }
            FileInputStream fileInputStream = new FileInputStream(filePath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, Constants.JSON_FILE_CHARSET);
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                resultList.add(tempString);
            }
            return resultList;
        } catch (Exception e) {
            log.error(ReturnConstantsEnum.TEMPLATE_FEIL_NOT_EXIT.getDesc(), e);
            throw new BusinessException(ReturnConstantsEnum.TEMPLATE_FEIL_NOT_EXIT, e);
        } finally {
            if (!Objects.isNull(reader)) {
                try {
                    reader.close();
                } catch (Exception e) {
                    log.error("BufferedReader 关闭失败", e);
                }
            }
        }
    }

    /**
     * 处理占位符
     *
     * @param readLineList
     * @param path
     * @param port
     */
    public static void dealPlaceholder(List<String> readLineList, String path, Integer port) {
        for (int i = 0; i < readLineList.size(); i++) {
            readLineList.set(i, readLineList.get(i).replace("${PATHNAME}", path).replace("${PORT}", String.valueOf(port)));
        }
    }

    /**
     * 将内容写入文件
     *
     * @param readLineList
     * @param filePath
     * @param fileName
     */
    public static void writeFileToHardDisk(List<String> readLineList, String filePath, String fileName) {
        BufferedWriter writer = null;
        try {
            File file = new File(filePath + Matcher.quoteReplacement(File.separator) + fileName + ".loc");
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file.getPath());
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, Constants.LOC_FILE_CHARSET);
            writer = new BufferedWriter(outputStreamWriter);
            for (String lineStr : readLineList) {
                writer.write(lineStr);
                writer.newLine();
            }
            writer.flush();
        } catch (Exception e) {
            log.error(ReturnConstantsEnum.WRITE_LOC_FILE_FAIL.getDesc(), e);
            throw new BusinessException(ReturnConstantsEnum.WRITE_LOC_FILE_FAIL, e);
        } finally {
            if (!Objects.isNull(writer)) {
                try {
                    writer.close();
                } catch (Exception e) {
                    log.error("BufferedWriter 关闭失败", e);
                }
            }
        }
    }

    /**
     * 删除LOC文件
     *
     * @param filePath
     * @param fileName
     */
    public static void deleteLocFile(String filePath, String fileName) {
        File file = new File(filePath + Matcher.quoteReplacement(File.separator) + fileName + ".loc");
        if (file.exists()) {
            file.delete();
        }
    }


    public static void main(String[] args) {
        List<String> readLineList = readLocFile("F:\\GitHub\\V2RayUI\\src\\main\\resources\\template\\nginxTemplate.loc");
        dealPlaceholder(readLineList, "shengouqiang", 567905);
        readLineList.stream().forEach(System.out::println);
        writeFileToHardDisk(readLineList, "F:/GitHub/V2RayUI/src/main/resources/template", "shengouqiang");
        deleteLocFile("F:/GitHub/V2RayUI/src/main/resources/template", "shengouqiang");
    }
}

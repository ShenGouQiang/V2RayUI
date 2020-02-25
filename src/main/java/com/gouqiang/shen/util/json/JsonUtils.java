package com.gouqiang.shen.util.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gouqiang.shen.exception.BusinessException;
import com.gouqiang.shen.constant.Constants;
import com.gouqiang.shen.constant.ReturnConstantsEnum;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Objects;

/**
 * @author shengouqiang
 * @date 2020/2/25
 */
@Slf4j
public class JsonUtils {

    /**
     * 将JSON数据格式化并保存到文件中
     *
     * @param jsonData 需要输出的json数
     * @param filePath 输出的文件地址
     * @return
     */
    public static void createJsonFile(Object jsonData, String filePath) throws IOException {
        String content = JSON.toJSONString(jsonData, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat);
        Writer write = null;
        try {
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            write = new OutputStreamWriter(new FileOutputStream(file), Constants.JSON_FILE_CHARSET);
            write.write(content);
            write.flush();
        } catch (Exception e) {
            log.error(ReturnConstantsEnum.WRITE_JSON_FILE_FAIL.getDesc(),e);
            throw new BusinessException(ReturnConstantsEnum.WRITE_JSON_FILE_FAIL);
        } finally {
            if (!Objects.isNull(write)) {
                write.close();
            }
        }
    }

    /**
     * 读取JSON文件
     *
     * @param Path
     * @return
     */
    public static String readFile(String Path) throws IOException {
        BufferedReader reader = null;
        StringBuffer laststr = new StringBuffer("");
        try {
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, Constants.JSON_FILE_CHARSET);
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr.append(tempString);
            }
        } catch (IOException e) {
            log.error(ReturnConstantsEnum.READ_JSON_FILE_FAIL.getDesc(),e);
            throw new BusinessException(ReturnConstantsEnum.READ_JSON_FILE_FAIL);
        } finally {
            if (reader != null) {
                reader.close();

            }
        }
        return laststr.toString();
    }
}

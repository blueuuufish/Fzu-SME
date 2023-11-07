package cn.iocoder.yudao.framework.excel.core.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel 工具类
 *
 * @author 芋道源码
 */
public class ExcelUtils {

    /**
     * 将列表以 Excel 响应给前端
     *
     * @param response 响应
     * @param filename 文件名
     * @param sheetName Excel sheet 名
     * @param head Excel head 头
     * @param data 数据列表哦
     * @param <T> 泛型，保证 head 和 data 类型的一致性
     * @throws IOException 写入失败的情况
     */
    public static <T> void write(HttpServletResponse response, String filename, String sheetName,
                                 Class<T> head, List<T> data) throws IOException {
        // 输出 Excel
        EasyExcel.write(response.getOutputStream(), head)
                .autoCloseStream(false) // 不要自动关闭，交给 Servlet 自己处理
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()) // 基于 column 长度，自动适配。最大 255 宽度
                .sheet(sheetName).doWrite(data);
        // 设置 header 和 contentType。写在最后的原因是，避免报错时，响应 contentType 已经被修改了
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
    }

    public static <T> List<T> read(MultipartFile file, Class<T> head) throws IOException {
       return EasyExcel.read(file.getInputStream(), head, null)
                .autoCloseStream(false)  // 不要自动关闭，交给 Servlet 自己处理
                .doReadAllSync();
    }

    public static <T> List<T> readCourseExcel(MultipartFile file, Class<T> head) throws IOException {

        return EasyExcel.read(file.getInputStream())
                .head(head)
                .registerReadListener(new ReadListener<T>() {
                    @Override
                    public void invoke(T data, AnalysisContext context) {
                        // 这里可以处理每一行的数据
                    }

                    @Override
                    public void doAfterAllAnalysed(AnalysisContext context) {
                        // 所有数据解析完成后的操作
                    }
                })
                .headRowNumber(2) // 设置表头行数，这里设置为1将自动跳过第一行
                .autoCloseStream(false) // 不要自动关闭流
                .doReadAllSync(); // 同步读取所有的sheet
    }

    public static <T> List<T> readCourseExcel(MultipartFile file, Class<T> head, int jumpRow) throws IOException {
        List<T> resultList = new ArrayList<>(); // 创建一个列表来存储结果

        EasyExcel.read(file.getInputStream())
                .head(head)
                .registerReadListener(new ReadListener<T>() {
                    @Override
                    public void invoke(T data, AnalysisContext context) {
                        // 这里可以处理每一行的数据
                        if (isValidRow(data)) { // 检查行是否有效
                            resultList.add(data); // 如果行有效，添加到结果列表
                        }
                    }

                    @Override
                    public void doAfterAllAnalysed(AnalysisContext context) {
                        // 所有数据解析完成后的操作
                    }

                    private boolean isValidRow(T data) {
                        // TODO: 实现检查行是否有效的逻辑, 目前是有一定问题的
                        for (Field field : data.getClass().getDeclaredFields()) {
                            field.setAccessible(true); // 设置私有属性可访问
                            try {
                                Object value = field.get(data);
                                if (value instanceof String stringValue) {
                                    if (stringValue.contains("制表人")) {
                                        return false; // 如果包含 "制表人"，则认为这行无效
                                    }
                                }
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                        return true; // 如果没有找到 "制表人"，则认为这行有效
                    }
                })
                .headRowNumber(jumpRow) // 设置表头行数，这里设置为1将自动跳过第一行
                .autoCloseStream(false) // 不要自动关闭流
                .doReadAllSync(); // 同步读取所有的sheet

        return resultList; // 返回结果列表，不包含无效行
    }

}

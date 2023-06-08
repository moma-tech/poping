package top.moma.example.export;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Slf4j
public class PdfExport {
  /*导出文件名称格式*/
  private static final String EXPORT_FILE_NAME = "Transaction_Flows_%s.%s";
  /*PDF导出文件后缀*/
  private static final String EXPORT_FILE_PDF_EXT = "pdf";
  /*导出模版路径前缀*/
  private static final String EXPORT_TEMPLATE_PATH_PREFIX = "templates/";
  /*PDF导出模版格式后缀*/
  private static final String EXPORT_PDF_TEMPLATE_SUFFIX = ".html";
  /*PDF导出模版名称*/
  private static final String EXPORT_PDF_TEMPLATE_NAME = "lamwa_trasaction_pdf-a";
  /*PDF导出模版字体文件*/
  private static final String EXPORT_PDF_FONT_RESOURCE_FILE = "templates/fonts/Roboto.ttf";
  private static final String EXPORT_PDF_FONT_RESOURCE_FILE2 = "templates/fonts/Hanzi.ttf";
  /*PDF导出模版字体名称*/
  private static final String EXPORT_PDF_FONT_RESOURCE_NAME = "Roboto";
  private static final String EXPORT_PDF_FONT_RESOURCE_NAME2 = "Hanzi";

  public void exportPdf() {
    try {
      TransactionExportResponse transactionExportResponse =
          TransactionExportResponse.builder()
              .name("test")
              .stmtDate("2022-01-01")
              .stmtStartDate("2022-01-01")
              .stmtEndDate("2022-01-04")
              .transList(
                  Arrays.asList(
                      TransactionExportResponse.Trans.builder()
                          .date("2022-01-01")
                          .accountNo("1")
                          .name("ali")
                          .amount("1000")
                          .type("Collection")
                          .channel("测试")
                          .build(),
                      TransactionExportResponse.Trans.builder()
                          .date("2022-01-01")
                          .accountNo("a")
                          .name("bob")
                          .amount("₦100000.229")
                          .type("Transfer")
                          .channel("赑屃")
                          .build()))
              .build();
      exportPdf(transactionExportResponse);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void exportPdf(TransactionExportResponse transactionExportResponse) {
    String exportPdfFileName = String.format(EXPORT_FILE_NAME, new Date(), EXPORT_FILE_PDF_EXT);
    try (OutputStream os = new FileOutputStream(exportPdfFileName)) {
      Map<String, Object> variables =
          JSON.parseObject(
              JSON.toJSONString(transactionExportResponse),
              new TypeReference<Map<String, Object>>() {});
      ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
      resolver.setPrefix(EXPORT_TEMPLATE_PATH_PREFIX);
      resolver.setSuffix(EXPORT_PDF_TEMPLATE_SUFFIX);
      SpringTemplateEngine templateEngine = new SpringTemplateEngine();
      templateEngine.setTemplateResolver(resolver);
      Context context = new Context();
      context.setVariable("pdfFileName", exportPdfFileName);
      context.setVariables(variables);
      String outputHtmlContent = templateEngine.process(EXPORT_PDF_TEMPLATE_NAME, context);
      PdfRendererBuilder builder = new PdfRendererBuilder();
      builder.useFont(
          () -> {
            try {
              return new ClassPathResource(EXPORT_PDF_FONT_RESOURCE_FILE).getInputStream();
            } catch (IOException e) {
              e.printStackTrace();
            }
            return null;
          },
          EXPORT_PDF_FONT_RESOURCE_NAME);
      builder.useFont(
          () -> {
            try {
              return new ClassPathResource(EXPORT_PDF_FONT_RESOURCE_FILE2).getInputStream();
            } catch (IOException e) {
              e.printStackTrace();
            }
            return null;
          },
          EXPORT_PDF_FONT_RESOURCE_NAME2);
      builder.useFastMode();
      builder.withHtmlContent(outputHtmlContent, null);
      builder.toStream(os);
      builder.run();
    } catch (Exception e) {
      log.error(
          "BalanceTransactionsController exportPdf error, data:{}",
          JSON.toJSONString(transactionExportResponse),
          e);
    }
  }

  private void exportPdf(
      HttpServletResponse response, TransactionExportResponse transactionExportResponse) {
    String exportPdfFileName = String.format(EXPORT_FILE_NAME, new Date(), EXPORT_FILE_PDF_EXT);
    response.setContentType(MediaType.APPLICATION_PDF_VALUE);
    response.setHeader(
        HttpHeaders.CONTENT_DISPOSITION,
        ContentDisposition.attachment()
            .filename(new String(exportPdfFileName.getBytes(), StandardCharsets.ISO_8859_1))
            .build()
            .toString());
    try (OutputStream os = response.getOutputStream()) {
      Map<String, Object> variables =
          JSON.parseObject(
              JSON.toJSONString(transactionExportResponse),
              new TypeReference<Map<String, Object>>() {});
      ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
      resolver.setPrefix(EXPORT_TEMPLATE_PATH_PREFIX);
      resolver.setSuffix(EXPORT_PDF_TEMPLATE_SUFFIX);
      SpringTemplateEngine templateEngine = new SpringTemplateEngine();
      templateEngine.setTemplateResolver(resolver);
      Context context = new Context();
      context.setVariable("pdfFileName", exportPdfFileName);
      context.setVariables(variables);
      String outputHtmlContent = templateEngine.process(EXPORT_PDF_TEMPLATE_NAME, context);
      PdfRendererBuilder builder = new PdfRendererBuilder();
      builder.useFont(
          () -> {
            try {
              return new ClassPathResource(EXPORT_PDF_FONT_RESOURCE_FILE).getInputStream();
            } catch (IOException e) {
              e.printStackTrace();
            }
            return null;
          },
          EXPORT_PDF_FONT_RESOURCE_NAME);
      builder.useFastMode();
      builder.withHtmlContent(outputHtmlContent, null);
      builder.toStream(os);
      builder.run();
    } catch (Exception e) {
      log.error(
          "BalanceTransactionsController exportPdf error, data:{}",
          JSON.toJSONString(transactionExportResponse),
          e);
    }
  }

  public static void main(String[] args) {
    PdfExport pdfExport = new PdfExport();
    pdfExport.exportPdf();
  }
}

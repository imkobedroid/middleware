package com.dragonest.pocket.compiler;

import com.dragonest.pocket.annotation.Path;
import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

/**
 * @author Dsh  imkobedroid@gmail.com
 * @date 11/30/21
 * @project Plugin Dev
 * @instructions
 */
@AutoService(Processor.class)
public class BRouterProcessor extends AbstractProcessor {


    //定义包名
    private static final String CREATE_PACKAGE_NAME = "com.ilongyuan.ex";
    //定义基础类名
    private static final String CREATE_CLASS_NAME = "RouterUtil";
    //后续文件操作使用
    private Filer filer;


    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
    }


    @Override
    public SourceVersion getSupportedSourceVersion() {
        return processingEnv.getSourceVersion();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new HashSet<>();
        types.add(Path.class.getCanonicalName());
        return types;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> elementsAnnotatedWith = roundEnvironment.getElementsAnnotatedWith(Path.class);
        if (elementsAnnotatedWith.size() < 1) {
            return false;
        }
        Map<String, String> collectMap = new HashMap<>();
        for (Element element : elementsAnnotatedWith) {
            //类节点
            TypeElement typeElement = (TypeElement) element;
            //获取全名，如果是类的话，包含完整的包名路径
            String className = typeElement.getQualifiedName().toString();
            String key = element.getAnnotation(Path.class).value();
            if (collectMap.get(key) == null) {
                //注解内容作为key，类名作为value，存入map中--此map是单个module的map
                collectMap.put(key, className + ".class");
            }

        }
        Writer writer = null;
        //为避免类名重复，生成的类名加上动态时间戳---此处实现与ARouter本身不一致，但更简单。
        //避免了从build.gradle中传递参数的步骤
        try {
            String activityName = CREATE_CLASS_NAME + System.currentTimeMillis();
            JavaFileObject sourceFile = filer.createSourceFile(CREATE_PACKAGE_NAME + "." + activityName);
            writer = sourceFile.openWriter();
            //代码生成
            StringBuilder routerBuild = new StringBuilder();
            for (String key : collectMap.keySet()) {
                routerBuild.append("BRouter.getInstance().addRouter(\"").append(key).append("\", ").append(collectMap.get(key)).append(");\n");
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("package " + CREATE_PACKAGE_NAME + ";\n");
            stringBuilder.append(
                    "import com.dragonest.pocket.routeframework.BRouter;\n" +
                    "import com.dragonest.pocket.routeframework.IRouter;\n" + "\n" +
                            "public class ").append(activityName).append(" implements IRouter {\n").append("\n").append("    @Override\n").append("    public void addRouter() {\n").append(routerBuild.toString()).append("    }\n").append("}");
            writer.write(stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}

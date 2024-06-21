package studentsAnotations;

import com.squareup.javapoet.ClassName;
import studentsAnotations.CreateObject;
import studentsAnotations.CreateStudent;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.*;

@SupportedAnnotationTypes({"studentsAnnotations.CreateObject", "studentsAnnotations.CreateStudent"})
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class GenerateProcessor extends AbstractProcessor {
    private Filer filer;
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        messager.printMessage(Diagnostic.Kind.NOTE, "Starting annotation processing...");

        Map<ClassName, List<ElementInfo>> result = new HashMap<>();

        // Обробка анотації @CreateObject
        for (Element annotatedElement : roundEnv.getElementsAnnotatedWith(CreateObject.class)) {
            if (annotatedElement.getKind() != ElementKind.INTERFACE) {
                error("Тільки інтерфейси можна анотувати @CreateObject", annotatedElement);
                continue;
            }
            TypeElement typeElement = (TypeElement) annotatedElement;
            ClassName className = ClassName.get(typeElement);
            result.putIfAbsent(className, new ArrayList<>());
        }

        // Обробка анотації @CreateStudent
        for (Element annotatedElement : roundEnv.getElementsAnnotatedWith(CreateStudent.class)) {
            if (annotatedElement.getKind() != ElementKind.CLASS) {
                error("Тільки класи можна анотувати @CreateStudent", annotatedElement);
                continue;
            }

            CreateStudent annotation = annotatedElement.getAnnotation(CreateStudent.class);
            TypeMirror typeMirror = annotatedElement.asType();
            ClassName className = getName(typeMirror);

            if (className != null && result.containsKey(className)) {
                result.get(className).add(new ElementInfo(annotation.value(), ClassName.get((TypeElement) annotatedElement)));
            }
        }

        // Генерація коду через FactoryBuilder
        try {
            new FactoryBuilder(filer, result).generate();
        } catch (IOException e) {
            error(e.getMessage());
        }

        return true;
    }

    private ClassName getName(TypeMirror typeMirror) {
        String packageName = null;
        String className = null;

        if (typeMirror instanceof DeclaredType) {
            DeclaredType declaredType = (DeclaredType) typeMirror;
            TypeElement typeElement = (TypeElement) declaredType.asElement();
            packageName = processingEnv.getElementUtils().getPackageOf(typeElement).getQualifiedName().toString();
            className = typeElement.getSimpleName().toString();
        }

        if (packageName == null || className == null) {
            error("Неправильний формат типу дзеркала: " + typeMirror.toString());
            return null;
        }

        return ClassName.get(packageName, className);
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(CreateObject.class.getCanonicalName());
        annotations.add(CreateStudent.class.getCanonicalName());
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    private void error(String message, Element element) {
        messager.printMessage(Diagnostic.Kind.ERROR, message, element);
    }

    private void error(String message) {
        messager.printMessage(Diagnostic.Kind.ERROR, message);
    }
}
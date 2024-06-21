package studentsAnotations;

import com.squareup.javapoet.ClassName;
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import java.util.*;

@SupportedAnnotationTypes("studentsAnotations.MaxMarks")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class MaxMarksProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(MaxMarks.class)) {
            if (element.getKind() == ElementKind.FIELD) {
                VariableElement variableElement = (VariableElement) element;
                MaxMarks maxMarks = variableElement.getAnnotation(MaxMarks.class);

                TypeMirror fieldType = variableElement.asType();
                if (isListOfInteger(fieldType)) {
                    ClassName className = ClassName.get((TypeElement) variableElement.getEnclosingElement());
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,
                            "Found @MaxMarks annotation on field " + variableElement.getSimpleName() +
                                    " in class " + className);

                    // Additional processing logic here if needed
                } else {
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                            "Field " + variableElement.getSimpleName() + " in " +
                                    variableElement.getEnclosingElement().getSimpleName() +
                                    " is not of type List<Integer>.");
                }
            }
        }
        return true;
    }

    private boolean isListOfInteger(TypeMirror typeMirror) {
        if (typeMirror.getKind() != TypeKind.DECLARED) {
            return false;
        }
        DeclaredType declaredType = (DeclaredType) typeMirror;
        if (!declaredType.asElement().toString().equals("java.util.List")) {
            return false;
        }
        List<? extends TypeMirror> typeArguments = declaredType.getTypeArguments();
        if (typeArguments.size() != 1) {
            return false;
        }
        return typeArguments.get(0).toString().equals("java.lang.Integer");
    }
}
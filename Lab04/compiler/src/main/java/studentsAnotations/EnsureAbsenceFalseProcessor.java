package studentsAnotations;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;
import javax.tools.Diagnostic;
import java.util.Set;

@SupportedAnnotationTypes("org.example.EnsureAbsenceFalse")
@SupportedSourceVersion(SourceVersion.RELEASE_8)

//Processor of annotation EnsureAbsenceFalse
public class EnsureAbsenceFalseProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(EnsureAbsenceFalse.class)) {
            if (element.getKind() == ElementKind.FIELD) {
                VariableElement variableElement = (VariableElement) element;
                if (variableElement.asType().getKind() == TypeKind.BOOLEAN) {
                    Object constantValue = variableElement.getConstantValue();
                    if (constantValue != null && Boolean.TRUE.equals(constantValue)) {
                        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                                "Field " + element.getSimpleName() + " in " + element.getEnclosingElement().getSimpleName() +
                                        " must be initialized to false.");
                    }
                } else {
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                            "Field " + element.getSimpleName() + " in " + element.getEnclosingElement().getSimpleName() +
                                    " is not of type boolean.");
                }
            }
        }
        return true;
    }
}
package com.diabin.min.compiler;

import com.diabin.min.annotations.AppRegisterGenerator;
import com.diabin.min.annotations.EntryGenerator;
import com.diabin.min.annotations.PayEntryGenerator;
import com.google.auto.service.AutoService;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 * Created by 邹俊 on 2018/3/24.
 *
 */
@SuppressWarnings("unused")
@AutoService(Processor.class)
public class MinProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        generateEntryCode(roundEnvironment);
        generatePayEntryCode(roundEnvironment);
        generateAppRegisterCode(roundEnvironment);
        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        final Set<String> types = new LinkedHashSet<>();
        final Set<Class<? extends Annotation>> annotations = getSupportedAnnotation();
        for (Class<? extends Annotation> annotation : annotations) {
            types.add(annotation.getCanonicalName());
        }
        return types;
    }

    private Set<Class<? extends Annotation>> getSupportedAnnotation() {
        final Set<Class<? extends Annotation>> annotation = new LinkedHashSet<>();
        annotation.add(EntryGenerator.class);
        annotation.add(PayEntryGenerator.class);
        annotation.add(AppRegisterGenerator.class);
        return annotation;
    }

    private void scan(RoundEnvironment env, Class<? extends Annotation> annotation,
                      AnnotationValueVisitor visitor) {

        for (Element elementType : env.getElementsAnnotatedWith(annotation)) {
            final List<? extends AnnotationMirror> mirrors = elementType.getAnnotationMirrors();

            for (AnnotationMirror annotationMirror : mirrors) {
                final Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues
                        = annotationMirror.getElementValues();

                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry
                        : elementValues.entrySet()) {
                    entry.getValue().accept(visitor, null);
                }
            }
        }
    }

    private void generateEntryCode(RoundEnvironment env) {
        final  EntryVisitor visitor= new EntryVisitor(processingEnv.getFiler());
        scan(env,EntryGenerator.class,visitor);
    }
    private void generatePayEntryCode(RoundEnvironment env) {
        final PayEntryVisitor payEntryVisitor =
                new PayEntryVisitor(processingEnv.getFiler());
        scan(env, PayEntryGenerator.class, payEntryVisitor);
    }

    private void generateAppRegisterCode(RoundEnvironment env) {
        final AppRegisterVisitor appRegisterVisitor =
                new AppRegisterVisitor(processingEnv.getFiler());
        scan(env, AppRegisterGenerator.class, appRegisterVisitor);
    }
}

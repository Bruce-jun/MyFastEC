package com.diabin.min.compiler;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

/**
 * Created by 邹俊 on 2018/3/24.
 *
 */

final class PayEntryVisitor extends SimpleAnnotationValueVisitor7<Void, Void> {

    private final Filer FILER;
    private String mPackageName = null;

    PayEntryVisitor(Filer FILER) {
        this.FILER = FILER;
    }

    @Override
    public Void visitString(String s, Void aVoid) {
        mPackageName = s;
        return aVoid;
    }

    @Override
    public Void visitType(TypeMirror typeMirror, Void aVoid) {
        generateJavaCode(typeMirror);
        return aVoid;
    }

    private void generateJavaCode(TypeMirror typeMirror) {
        final TypeSpec targetActivity = TypeSpec
                .classBuilder("WXPayEntryActivity")
                .addModifiers(Modifier.PUBLIC)
                .addModifiers(Modifier.FINAL)
                .superclass(TypeName.get(typeMirror))
                .build();

        final JavaFile javaFile = JavaFile.builder(mPackageName + ".wxapi", targetActivity)
                .addFileComment("微信支付入口文件")
                .build();

        try {
            javaFile.writeTo(FILER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

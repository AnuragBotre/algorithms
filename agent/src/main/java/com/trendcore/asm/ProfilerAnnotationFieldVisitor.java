package com.trendcore.asm;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.FieldVisitor;

public class ProfilerAnnotationFieldVisitor extends AnnotationVisitor {

    private String category;

    public ProfilerAnnotationFieldVisitor(int api, AnnotationVisitor annotationVisitor) {
        super(api, annotationVisitor);
    }

    @Override
    public void visit(String name, Object value) {
        super.visit(name, value);
        switch (name) {
            case "category":
                this.category = (String) value;
                break;
        }
    }

    public String getCategory() {
        return category;
    }
}

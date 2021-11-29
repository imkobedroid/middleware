package com.longyuan.dev;


import groovyjarjarasm.asm.ClassVisitor;
import groovyjarjarasm.asm.MethodVisitor;
import groovyjarjarasm.asm.Opcodes;

/**
 * @author Dsh  imkobedroid@gmail.com
 * @date 11/26/21
 * @project Plugin Dev
 * @instructions
 */
public class SunnyClassVisitor extends ClassVisitor {

    private String className;

    public SunnyClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        this.className = name;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        System.out.println("LifecycleClassVisitor : visitMethod : " + name);
        MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
        //匹配FragmentActivity
        if ("android/support/v4/app/FragmentActivity".equals(className)) {
            if ("onCreate".equals(name) ) {
                //处理onCreate
                return new SunnyMethodVisitor(mv);
            } else if ("onDestroy" .equals(name)) {
                //处理onDestroy
                return new SunnyMethodVisitor(mv);
            }
        }
        return mv;

    }


    @Override
    public void visitEnd() {
        super.visitEnd();
    }
}

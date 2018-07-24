package com.uber.motif.compiler.graph

import com.squareup.javapoet.ClassName
import com.uber.motif.compiler.asTypeElement
import com.uber.motif.compiler.codegen.className
import com.uber.motif.compiler.names.Names
import javax.lang.model.element.TypeElement
import javax.lang.model.type.DeclaredType

fun ClassName.qualifiedName(): String {
    return "${packageName()}.${simpleName()}"
}

object ClassNames {

    fun scopeImpl(scopeType: DeclaredType): ClassName {
        return scopeImpl(scopeType.asTypeElement())
    }

    fun scopeImpl(scopeType: TypeElement): ClassName {
        val scopeClassName = scopeType.className
        val prefix = scopeClassName.simpleNames().joinToString("_")
        return ClassName.get(scopeClassName.packageName(), "${prefix}Impl")
    }

    fun generatedParentInterface(scopeType: DeclaredType): ClassName {
        return scopeImpl(scopeType).nestedClass(Names.PARENT_INTERFACE_NAME)
    }
}
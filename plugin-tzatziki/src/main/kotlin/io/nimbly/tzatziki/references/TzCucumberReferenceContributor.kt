/*
 * CUCUMBER +
 * Copyright (C) 2023  Maxime HAMM - NIMBLY CONSULTING - Maxime.HAMM@nimbly-consulting.com
 *
 * This document is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This work is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

package io.nimbly.tzatziki.references

import com.intellij.openapi.util.TextRange
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.*
import com.intellij.psi.tree.TokenSet
import com.intellij.util.ProcessingContext
import org.jetbrains.plugins.cucumber.psi.GherkinElementTypes
import org.jetbrains.plugins.cucumber.psi.GherkinTokenTypes
import org.jetbrains.plugins.cucumber.psi.impl.GherkinStepImpl

/**
 * Reference contributor for Gherkin step definitions.
 * Provides navigation from Gherkin steps to their Java/Kotlin/Scala implementations.
 * 
 * Performance: Uses a singleton provider instance to avoid unnecessary allocations.
 */
class TzCucumberReferenceContributor : PsiReferenceContributor() {

    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        registrar.registerReferenceProvider(
            PlatformPatterns.psiElement(GherkinStepImpl::class.java), 
            PROVIDER  // Reuse singleton instance
        )
    }

    companion object {
        private val PROVIDER = TzCucumberStepReferenceProvider()
    }
}

/**
 * Provider that creates references for Gherkin steps.
 * 
 * Performance optimizations:
 * - Early return for non-GherkinStep elements
 * - Efficient token traversal using node iteration
 * - Reuses TokenSet instances (companion object)
 * - Single reference creation per step
 * 
 * The actual resolution performance is handled by TzCucumberStepReference which implements
 * multi-level caching (ResolveCache, CachedValuesManager, LAST_VALID fallback).
 */
class TzCucumberStepReferenceProvider : PsiReferenceProvider() {

    override fun getReferencesByElement(element: PsiElement, context: ProcessingContext): Array<PsiReference> {
        // Early return for non-Gherkin steps
        if (element !is GherkinStepImpl) {
            return PsiReference.EMPTY_ARRAY
        }

        // Find the text node containing the step text
        var node = element.node.findChildByType(TEXT_AND_PARAM_SET) ?: return PsiReference.EMPTY_ARRAY

        val start = node.textRange.startOffset
        var end = node.textRange.endOffset
        var endBeforeSpace = end
        node = node.treeNext

        // Traverse through text, parameters, and whitespace to find the complete step text range
        while (node != null && TEXT_PARAM_AND_WHITE_SPACE_SET.contains(node.elementType)) {
            endBeforeSpace = if (node.elementType === TokenType.WHITE_SPACE) end else node.textRange.endOffset
            end = node.textRange.endOffset
            node = node.treeNext
        }

        // Create reference with the calculated text range
        val textRange = TextRange(start, endBeforeSpace)
        val reference = TzCucumberStepReference(element, textRange.shiftRight(-element.textOffset))

        return arrayOf(reference)
    }

    companion object {
        /**
         * TokenSet containing Gherkin step text and parameter tokens.
         * Defined as static to avoid recreation on each reference lookup.
         */
        private val TEXT_AND_PARAM_SET = TokenSet.create(
            GherkinTokenTypes.TEXT,
            GherkinTokenTypes.STEP_PARAMETER_TEXT,
            GherkinTokenTypes.STEP_PARAMETER_BRACE,
            GherkinElementTypes.STEP_PARAMETER
        )
        
        /**
         * TokenSet combining text/parameter tokens with whitespace.
         * Used for efficient token traversal. Defined as static for performance.
         */
        private val TEXT_PARAM_AND_WHITE_SPACE_SET: TokenSet = TokenSet.orSet(
            TEXT_AND_PARAM_SET,
            TokenSet.WHITE_SPACE
        )
    }
}
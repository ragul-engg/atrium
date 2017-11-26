package ch.tutteli.atrium.assertions.throwable.thrown

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.IBasicAssertion
import ch.tutteli.atrium.assertions.any.narrow.IAnyNarrow
import ch.tutteli.atrium.assertions.throwable.thrown.IThrowableThrown.IAbsentThrowableMessageProvider
import ch.tutteli.atrium.assertions.throwable.thrown.IThrowableThrown.ICreator
import ch.tutteli.atrium.assertions.throwable.thrown.builders.ThrowableThrownBuilder
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.IRawString
import ch.tutteli.atrium.reporting.translating.ITranslatable
import kotlin.reflect.KClass

/**
 * Defines the contract for sophisticated a [Throwable] was thrown assertions.
 *
 * An assertion starts with a [ThrowableThrownBuilder] and is typically built up by an [IAbsentThrowableMessageProvider]
 * and an [IAnyNarrow.IDownCastFailureHandler] which are passed to a [ICreator] which finally builds the assertion.
 */
interface IThrowableThrown {
    /**
     * Provides a message which can be used in reporting to represent the case that no [Throwable] at all was thrown.
     */
    interface IAbsentThrowableMessageProvider {
        /**
         * The message can be used in reporting to represent the case that no [Throwable] at all was thrown.
         */
        val message: IRawString
    }

    /**
     * Represents the final step of a sophisticated a [Throwable] was thrown assertion builder which creates
     * the [IAssertionGroup] as such.
     *
     * @param TExpected The type of the [Throwable] which is expected to be thrown.
     */
    interface ICreator<TExpected : Throwable> {
        /**
         * Executes the [act][ThrowableThrownBuilder.act] lambda of the given [throwableThrownBuilder], catches any
         * thrown [Throwable], creates based on it a corresponding [IAssertion] representing the sophisticated
         * assertion and also checks whether it holds or not.
         *
         * @param throwableThrownBuilder The [ThrowableThrownBuilder] containing inter alia the
         *        [act][ThrowableThrownBuilder.act] lambda.
         * @param description The [description][IBasicAssertion.description] of the resulting [IBasicAssertion].
         * @param expectedType The expected type of the [Throwable] used for casting and probably in reporting.
         * @param assertionCreator The assertion creator which defines subsequent assertions for the [Throwable] in
         *        case it was thrown as expected and is of the expected type [TExpected].
         */
        fun executeActAndCreateAssertion(
            throwableThrownBuilder: ThrowableThrownBuilder,
            description: ITranslatable,
            expectedType: KClass<TExpected>,
            assertionCreator: IAssertionPlant<TExpected>.() -> Unit
        )
    }
}

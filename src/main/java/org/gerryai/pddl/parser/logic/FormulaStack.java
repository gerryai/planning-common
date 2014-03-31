package org.gerryai.pddl.parser.logic;

import java.util.Stack;

import static com.google.common.base.Preconditions.checkState;
import static java.lang.String.format;

/**
 * Stack for recording formulas that haven't been finished yet.
 * Each item on the stack has a type and a stash of the formulas that had been built previously but not collected yet.
 */
public class FormulaStack {

    private Stack<FormulaStackItem> formulaStack = new Stack<>();

    /**
     * Push a new formula onto the stack.
     * @param type the type of the new formula being built
     * @param queue the list of formula built so far for the previous stack entry
     */
    public void push(final FormulaType type, final FormulaStash queue) {
        formulaStack.push(new FormulaStackItem(type, queue));
    }

    /**
     * Pop a formula from the stack and add it to the queue awaiting collection.
     * @param type the type of formula to expect
     * @return the formula stash that was stashed before the current formula was started
     */
    public FormulaStash pop(final FormulaType type) {
        checkType(type);
        return formulaStack.pop().getFormulaStash();
    }

    /**
     * Peek at the top of the stack and check that the formula is of the expected type.
     * @param type the formula type expected
     */
    public void checkType(final FormulaType type) {
        checkState(type.equals(formulaStack.peek().getFormulaType()),
                format("Expected the top of the stack to be %s", type));
    }

    /**
     * Private class describing a stack item.
     * Each entry on the stack defines the type of formula being built and the list of formulas that had been built
     * as part of the previous stack entry but have not yet been collected.
     */
    private class FormulaStackItem {
        private FormulaType formulaType;
        private FormulaStash formulaStash;

        /**
         * Constructor.
         * @param type the formula type
         * @param queue the formula queue from before we started building this current formula
         */
        public FormulaStackItem(final FormulaType type, final FormulaStash queue) {
            this.formulaStash = queue;
            this.formulaType = type;
        }

        /**
         * Get the formula type.
         * @return the formula type
         */
        public FormulaType getFormulaType() {
            return formulaType;
        }

        /**
         * Get the formula queue.
         * @return the formula queue
         */
        public FormulaStash getFormulaStash() {
            return formulaStash;
        }
    }
}

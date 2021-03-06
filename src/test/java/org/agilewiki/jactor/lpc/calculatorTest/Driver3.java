package org.agilewiki.jactor.lpc.calculatorTest;

import org.agilewiki.jactor.RP;
import org.agilewiki.jactor.SimpleRequest;
import org.agilewiki.jactor.SimpleRequestReceiver;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.stateMachine.ObjectFunc;
import org.agilewiki.jactor.stateMachine.StateMachine;

/**
 * Test code.
 */
public class Driver3 extends JLPCActor implements SimpleRequestReceiver {
    @Override
    public void processRequest(SimpleRequest request, final RP rp)
            throws Exception {
        final Calculator calculator = new Calculator();
        calculator.initialize(getMailbox());
        final SMBuilder smb = new SMBuilder();
        smb._send(calculator, new Set(1));
        smb._send(calculator, new Add(2));
        smb._send(calculator, new Multiply(3), "result");
        smb._return(new ObjectFunc() {
            @Override
            public Object get(StateMachine sm) {
                return sm.get("result");
            }
        });
        smb.call(rp);
    }
}

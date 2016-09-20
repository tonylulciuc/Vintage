package com.vintage.vintage.server.base;

/**
 * Created by Antony Lulciuc on 9/20/2016.
 */
public class RunnableInstruction implements Runnable {
    private Object[] m_Data;
    private Instruction m_Instruction;

    /**
     * Constructor
     * @param _instruction [in] what to execute
     * @param _data [in] data to process
     */
    public RunnableInstruction(Instruction _instruction, Object[] _data){
        m_Instruction = _instruction;
        m_Data = _data;
    }

    /**
     * Work to be done
     */
    @Override
    public void run(){
        m_Instruction.execute(m_Data);
    }
}

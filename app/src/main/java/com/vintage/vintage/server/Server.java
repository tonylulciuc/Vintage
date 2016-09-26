package com.vintage.vintage.server;

import android.content.Context;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.vintage.vintage.server.base.GetMatchbox;
import com.vintage.vintage.server.base.Instruction;
import com.vintage.vintage.server.base.Result;
import com.vintage.vintage.server.base.RunnableInstruction;

/**
 * Created by Antony Lulciuc on 9/20/2016.
 */

public class Server {
    public static final int DATA_MATCHBOX = 0x01;
    public static final int DATA_ALL      = 0xFF;
    private Context m_Context;
    private BackendlessUser m_bkUser;

    public Server(Context _context){
        m_Context = _context;
        Backendless.initApp(m_Context,
                "997B023F-3500-EAA5-FFFD-CE09804A4A00",
                "A97F753C-EAEC-3B05-FF38-CADE057FEA00",
                "v1");
    }

    public void login(final String _email, final String _password){
        Thread loginThread = new Thread(new Runnable() {
            @Override
            public void run() {
                m_bkUser = Backendless.UserService.login(_email, _password);
            }
        });

        loginThread.start();
    }

    /**
     *
     * @param _result
     * @param _query
     * @param _for
     */
    void get(Result _result, String _query, int _for){
        Instruction instruction = null;
        Thread work;

        switch (_for){
            case DATA_MATCHBOX:
                instruction = new GetMatchbox();
                break;
            case DATA_ALL:
                break;
        }

        work = new Thread(new RunnableInstruction(instruction, new Object[]{_result, _query}));
        work.start();
    }

}

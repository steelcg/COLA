package com.alibaba.cola.statemachine;

import com.alibaba.cola.statemachine.impl.StateMachineException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * StateMachineFactory
 *
 * @author Frank Zhang
 * @date 2020-02-08 10:21 PM
 */
public class StateMachineFactory {
    static Map<String /* machineId */, StateMachine> stateMachineMap = new ConcurrentHashMap<>();

    public static <S, E, C> void register(StateMachine<S, E, C> stateMachine){
        stateMachineMap.put(stateMachine.getMachineId(), stateMachine);
    }

    public static <S, E, C> StateMachine<S, E, C> get(String machineId){
        StateMachine stateMachine = stateMachineMap.get(machineId);
        if(stateMachine == null){
            throw new StateMachineException("There is no stateMachine instance for "+machineId+", please build it first");
        }
        return stateMachine;
    }
}

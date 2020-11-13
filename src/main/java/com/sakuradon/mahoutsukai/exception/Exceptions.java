package com.sakuradon.mahoutsukai.exception;

/**
 * @author SakuraDon
 */
public class Exceptions {

    public static final SystemException ELEMENT_CREATE_FAILED =
            new SystemException(200, "element create failed");

    public static final SystemException ELEMENT_FILE_NOT_FOUND =
            new SystemException(201, "element not found");

    public static final SystemException LOCAL_SCREEN_READ_FAILED =
            new SystemException(202, "local screen read failed");

    public static final SystemException ADB_EXEC_FAILED =
            new SystemException(300, "adb exec failed");

    public static final SystemException LOAD_CONFIG_FAILED =
            new SystemException(100, "load config failed");

    public static final SystemException BOOT_PARAMETER_ERROR =
            new SystemException(101, "boot parameter error");

    public static final SystemException NO_SELECT_WORKFLOW =
            new SystemException(102, "there are one more enable workflow, but not select");

    public static final SystemException WORKFLOW_NOT_FOUND =
            new SystemException(103, "workflow not found");

    public static final SystemException TASK_NOT_FOUND =
            new SystemException(104, "task not found");

    public static final SystemException ILLEGAL_WORKFLOW =
            new SystemException(105, "illegal workflow, please use the EnableWorkflow annotation");

    public static final SystemException ILLEGAL_TASK =
            new SystemException(106, "illegal task, please use the EnableTask annotation");

    public static final SystemException NO_ENABLE_WORKFLOW =
            new SystemException(107, "there are no enable workflow to execute");

}

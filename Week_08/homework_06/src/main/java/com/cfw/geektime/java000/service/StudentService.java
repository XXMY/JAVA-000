package com.cfw.geektime.java000.service;

import com.cfw.geektime.java000.mapper.StudentMapper;
import com.cfw.geektime.java000.model.StudentModel;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

    private StudentMapper studentMapper;

    public StudentService(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    /**
     * Execute XA.
     *
     * @return int type
     */
    @Transactional
    @ShardingTransactionType(TransactionType.XA)
    public int insert(StudentModel studentModel){
        return insertTableA(studentModel)+insertTableB(studentModel) ;
    }

    private int insertTableA(StudentModel studentModel){
        return studentMapper.insertStudent(studentModel.getId(),studentModel.getNumber(),
                studentModel.getName(),studentModel.getAge(),
                studentModel.getClazz(),studentModel.getDepartment());
    }

    private int insertTableB(StudentModel studentModel){
        // to throw exception
//        int result = 1/0;
        return studentMapper.insertStudent(studentModel.getId()+13,studentModel.getNumber(),
                studentModel.getName(),studentModel.getAge()+12,
                studentModel.getClazz(),studentModel.getDepartment());
    }
}

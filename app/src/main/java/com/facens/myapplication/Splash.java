package com.facens.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    //Criação de uma variável Timer, usada pra agendar tarefas, e adição de um novo valor a ela, e também a criação de uma TimerTask, que é a tarefa a ser executada depois de agendada
    private final Timer timer = new Timer();
    TimerTask timerTask;

    //criação de um método de restauração do estado de uma imagem
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Setando a variável timerTask (tarefa a ser executada depois de agendada)
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run(){
                        gotoMainActivity();
                    }
                });
            }
        };
        //Setando o "agendamento" da tarefa, com o delay de 3000
        timer.schedule(timerTask, 3000);
    }
    //Método para chamar a classe MainActivity
    private void gotoMainActivity(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.Scolaris.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 *
 * @author tavep
 */
public class Criptografia {

   
    

    /**
     * Função para transformar texto em hashMD5
     */
    public static String getMD5(String texto) {
        try {

            // O método estático getInstance é chamado com hash MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // O método digest() é chamado para calcular a hash da mensagem
            // E enfim temos o vetor da mensagem
            byte[] messageDigest = md.digest(texto.getBytes());

            // Convertemos o vetor de bytes em um BigInt
            BigInteger no = new BigInteger(1, messageDigest);

            // A mensagem em BigInt é convertida para hexadecimal
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } // Em caso de erro, é lançada uma exceção
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

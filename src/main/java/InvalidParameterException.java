/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author dcalha
 */
public class InvalidParameterException extends RuntimeException {

    public InvalidParameterException() {
        super("Erro no tamanho do array. Verifique os parâmetros");
    }

}

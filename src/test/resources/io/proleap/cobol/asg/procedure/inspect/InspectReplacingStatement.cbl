 IDENTIFICATION DIVISION.
 PROGRAM-ID. INSPCTSTMT.
 PROCEDURE DIVISION.
    INSPECT SOMEDATA1 
       REPLACING
          CHARACTERS BY 'C' AFTER INITIAL 'A'
          FIRST 'B' BY 'C' BEFORE INITIAL 'A'.

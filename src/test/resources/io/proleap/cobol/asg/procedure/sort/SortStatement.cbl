 IDENTIFICATION DIVISION.
 PROGRAM-ID. MERGESTMT.
 PROCEDURE DIVISION.
    SORT SOMEFILE1
       ON DESCENDING KEY SOMEID1
       ON ASCENDING KEY SOMEID2 SOMEID3
       WITH DUPLICATES IN ORDER
       COLLATING SEQUENCE IS SOMEALPHA1 SOMEALPHA2
          FOR ALPHANUMERIC IS SOMEALPHA3
          FOR NATIONAL IS SOMEALPHA4
       INPUT PROCEDURE IS PROC1 THRU PROC3
       USING SOMEFILE2
       OUTPUT PROCEDURE IS PROC1 THRU PROC3
       GIVING SOMEFILE3 NO REWIND.
 PROC1.
     Display "Proc1".
 PROC2.
     Display "Proc2".
 PROC3.
     Display "Proc3".
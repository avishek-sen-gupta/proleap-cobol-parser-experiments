Analysing V7523438
- Issues
    - Does not have Procedure DIVISION
    - Had to remove first 5 lines (in main program)
    - Small change in grammar to allow RECORD in COPY IDMS RECORD statement
    - V75RPROF not found - comment out COPY IDMS V75RPROF. to continue
    - J05RDBOX not found - comment out COPY  IDMS J05RDBOX. to continue
    - F17RUR04 not found - comment out COPY  IDMS F17RUR04. to continue
    - J00CW004 not found - comment out COPY  IDMS J00CW004. to continue
    - J00CW005 not found - comment out COPY  IDMS J00CW005. to continue
    - Removed lines LANGUAGE IS COBOL from all files to continue - because of syntax ambiguity with IS
    - J00CW005 not found - comment out COPY IDMS V75Z25OF. to continue
    - J00CW005 not found - comment out COPY IDMS V75Z25ON. to continue
    - J00CW005 not found - comment out COPY IDMS V75Z38OC. to continue
    -

Analysing V751C931
- Issues
    - J00CW005 not found - comment out COPY IDMS V75CWTI3. to continue
    - J00CW005 not found - comment out COPY IDMS F17RUR04. to continue
    - J00CW005 not found - comment out COPY IDMS F00REUWK. to continue
    - J00CW005 not found - comment out COPY IDMS V75CW606. to continue
    - J00CW005 not found - comment out COPY IDMS V75RPROF. to continue
    - J00CW005 not found - comment out COPY IDMS CGACVTWS. to continue (this is present in sample code though)
    - Suffix 2000M removed 03  CENTURY      PIC XX.                                      2000M
    - Following lines were misaligned and being manged:
      DEL RECORD V75CWEUR.
      ADD RECORD V75CWEUR.
    - DEL/ADD records are inlined and become invalid becuause they become part of the record structure...needs eventual removal
    - MAIN-B, MAIN-Y, etc. what are these? Valid COBOL markers or IDMS-specific?
    - 

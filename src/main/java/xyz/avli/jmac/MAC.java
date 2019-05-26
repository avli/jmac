package xyz.avli.jmac;

public class MAC {

    // The instruction set.
    private static final short PSH = 0;
    private static final short ADD = 1;
    private static final short POP = 2;
    private static final short HLT = 3;

    private int[] program = {
            PSH, 5,
            PSH, 6,
            ADD,
            POP,
            HLT
    };

    private int ip = 0;
    private int sp = -1;

    private boolean running = true;

    private int[] stack = new int[256];

    private static MAC INSTANCE;

    private static MAC getInstance() {
        if (INSTANCE == null)
            INSTANCE = new MAC();
        return INSTANCE;
    }

    private int fetch() {
        return program[ip];
    }

    private void eval(int instr) {
        switch (instr) {
            case HLT:
                running = false;
                System.out.println("done");
                break;
            case PSH:
                sp++;
                stack[sp] = program[++ip];
                break;
            case POP:
                int val_popped = stack[sp--];
                System.out.println("popped " + val_popped);
                break;
            case ADD:
                int a = stack[sp--];
                int b = stack[sp--];
                int result = a + b;
                sp++;
                stack[sp] = result;
                break;
        }
    }

    public static void run() {
        MAC vm = getInstance();
        while (vm.running) {
            vm.eval(vm.fetch());
            vm.ip++;
        }
    }

    public static void main(String[] args) {
        run();
    }
}

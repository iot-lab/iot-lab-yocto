#! /bin/sh

NTPQ="/usr/sbin/ntpq"
REFTIME_CMD="${NTPQ} -c rv | sed -n '/reftime/ s/reftime=\([^ ]*\) .*/\1/p'"

reftime() {
    ${NTPQ} -c rv | sed -n '/reftime/ s/reftime=\([^ ]*\) .*/\1/p'
}

wait_synced() {
    while [[ "00000000.00000000" == $(reftime) ]]
    do
        echo
        ${NTPQ} -c rv | grep -e reftime -e clock
        sleep 15
    done
}


time wait_synced

echo
${NTPQ} -c rv

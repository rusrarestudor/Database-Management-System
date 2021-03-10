library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity MemoUnit is
    port( memWrite : in STD_LOGIC;
           clk : in std_logic;
           aluRes : in STD_LOGIC_VECTOR (15 downto 0);
           rd2 : in STD_LOGIC_VECTOR (15 downto 0);
           memData : out STD_LOGIC_VECTOR (15 downto 0);
           aluReso : out STD_LOGIC_VECTOR (15 downto 0));
end MemoUnit;

architecture Behavioral of MemoUnit is
begin

component RAM is
    port (  clk : in std_logic;
            we : in std_logic;
            en : in std_logic;
            addr : in std_logic_vector(7 downto 0);
            di : in std_logic_vector(15 downto 0);
            do : out std_logic_vector(15 downto 0));
end component;

begin
aluReso <= aluRes;
ram: RAM port map (clk, memWrite, '1', aluRes(7 downto 0), rd2, memData);
end Behavioral;
